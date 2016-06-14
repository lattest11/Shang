package digimatic.shangcommerce.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import digimatic.shangcommerce.R;
import digimatic.shangcommerce.adapter.PaymentMethodAdapter;
import digimatic.shangcommerce.callback.PaymentMethodCallback;
import digimatic.shangcommerce.connection.callback.UICallback;
import digimatic.shangcommerce.connection.threadconnection.execute.CartExecute;
import digimatic.shangcommerce.connection.threadconnection.execute.OrderExecute;
import digimatic.shangcommerce.daocontroller.CartDetailController;
import digimatic.shangcommerce.daocontroller.CartItemController;
import digimatic.shangcommerce.daocontroller.CustomerController;
import digimatic.shangcommerce.model.OrderSummary;
import digimatic.shangcommerce.model.PaymentMethod;
import digimatic.shangcommerce.staticfunction.Font;
import greendao.CartDetail;
import greendao.Customer;

/**
 * Created by user on 3/16/2016.
 */
public class PaymentMethodActivity extends BaseActivity implements View.OnClickListener, PaymentMethodCallback {

    private RelativeLayout rltBack;
    private TextView txtTitle;
    private TextView txtContinue;

    private ListView listView;
    private PaymentMethodAdapter adapter;
    private List<PaymentMethod> listData;


    //////////////// setup paypal
    private float deliveryFee = 0;

    private String PAY_ID = "";

    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX;

    // note that these credentials will differ between live & sandbox environments.
    private static final String CONFIG_CLIENT_ID = "AWV3Vl3P5kdGORcQcHimVb44SYwhhUcAb3JraSaq2naXr0HG5ZeISEzKv9zQFT9V8P5Rjd8iuN-MFp6P";

    private static final int REQUEST_CODE_PAYMENT = 1;

    private static PayPalConfiguration config;
    /////// setup paypal

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        initView();
        initData();

        initPaypal();
    }

    private void initView() {
        rltBack = (RelativeLayout) findViewById(R.id.rltBack);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtContinue = (TextView) findViewById(R.id.txtContinue);
        listView = (ListView) findViewById(R.id.listView);

        rltBack.setOnClickListener(this);
        txtContinue.setOnClickListener(this);

        Font font = new Font(this);
        font.overrideFontsLight(findViewById(R.id.root));
        font.overrideFontsBold(txtTitle);
    }

    private void initData() {
        listData = new ArrayList<>();

        adapter = new PaymentMethodAdapter(this, listData, this);
        listView.setAdapter(adapter);

        getDeliveryType();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rltBack:
                finish();
                break;
            case R.id.txtContinue:
//                checkoutByPaypal(100);
                boolean isChosen = false;
                for (PaymentMethod paymentMethod : listData) {
                    if (paymentMethod.isChosen) {
                        isChosen = true;
                        OrderSummary.payment_method = paymentMethod.getCode();
                        break;
                    }
                }
                if (isChosen) {
//                    Intent intent = new Intent(this, OrderSummaryActivity.class);
//                    startActivity(intent);
                    if (OrderSummary.payment_method.equalsIgnoreCase("paypalmobile")) {
                        CartDetail cartDetail = CartDetailController.getCurrentCartDetail(this);
                        if (cartDetail != null) {
                            checkoutByPaypal(cartDetail.getGrand_total());
                        }
                    } else {
                        createOrder();
                    }
                } else {
                    showToastError(getString(R.string.choose_payment_method));
                }
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        for (int i = 0; i < listData.size(); i++) {
            listData.get(i).setIsChosen(false);
        }
        listData.get(position).setIsChosen(true);
        adapter.setListData(listData);
    }

    private void createOrder() {
        JSONObject order = new JSONObject();
        final long entity_id = CartDetailController.getCurrentCartDetail(this).getEntity_id();
        try {
            order.put("quote_id", entity_id);
            order.put("customer_id", CustomerController.getCurrentCustomer(this).getEntity_id());
            order.put("payment_method", OrderSummary.payment_method);
            order.put("shipping_method", OrderSummary.delivery_code);
            order.put("customer_note", OrderSummary.remark);
            if (OrderSummary.payment_method.equalsIgnoreCase("paypalmobile")) {
                order.put("paypal_trans_id", PAY_ID);
            }
            UICallback callback = new UICallback() {
                @Override
                public void onResult(boolean success, String data) {
                    if (success) {
                        try {
                            JSONObject object = new JSONObject(data);
                            long increment_id = object.getLong("increment_id");
                            showToastOk("Your order has been successfully processed!");
                            CartDetailController.clearAll(PaymentMethodActivity.this);
                            CartItemController.clearAll(PaymentMethodActivity.this);
                            Customer customer = CustomerController.getCurrentCustomer(PaymentMethodActivity.this);
                            customer.setCart_items_count(0);
                            CustomerController.insertOrUpdate(PaymentMethodActivity.this, customer);
                            Intent intent = new Intent(PaymentMethodActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            Intent intentTransactionHistory = new Intent(PaymentMethodActivity.this, TransactionHistoryActivity.class);
                            startActivity(intentTransactionHistory);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            showToastError(e.getMessage());
                        }
                        hideProgressDialog();
                    } else {
                        showToastError(data);
                        getCartToCheckIsOrderCreated(entity_id);
                    }
                }
            };
            OrderExecute.createOrder(this, callback, order.toString());
            showProgressDialog(false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getDeliveryType() {
        UICallback callback = new UICallback() {
            @Override
            public void onResult(boolean success, String data) {
                if (success) {
                    try {
                        List<PaymentMethod> list = new ArrayList<>();
                        JSONArray array = new JSONArray(data);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            String code = object.getString("code");
                            String label = object.getString("label");
                            boolean isChoose = false;
                            if (i == 0) {
                                isChoose = true;
                            }
                            if (code.equals("cashondelivery") || code.equals("paypalmobile")) {
                                list.add(new PaymentMethod(code, label, isChoose));
                            }
                        }
                        listData.clear();
                        listData.addAll(list);
                        adapter.setListData(listData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        showToastError(e.getMessage());
                    }
                } else {
                    showToastError(data);
                }
                hideProgressDialog();
            }
        };
        CartExecute.getPaymentMethod(this, callback);
        showProgressDialog(false);
    }

    private void getCartToCheckIsOrderCreated(final long old_entity_id) {
        UICallback callback = new UICallback() {
            @Override
            public void onResult(boolean success, String data) {
                if (success) {
                    try {
                        JSONObject cart = new JSONObject(data);
                        long entity_id = cart.optLong("entity_id", 0);
                        if (old_entity_id == entity_id) {
                            createOrder();
                        } else {
                            showToastOk("Your order has been successfully processed!");
                            CartDetailController.clearAll(PaymentMethodActivity.this);
                            CartItemController.clearAll(PaymentMethodActivity.this);
                            Customer customer = CustomerController.getCurrentCustomer(PaymentMethodActivity.this);
                            customer.setCart_items_count(0);
                            CustomerController.insertOrUpdate(PaymentMethodActivity.this, customer);
                            Intent intent = new Intent(PaymentMethodActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            Intent intentTransactionHistory = new Intent(PaymentMethodActivity.this, TransactionHistoryActivity.class);
                            startActivity(intentTransactionHistory);

                            hideProgressDialog();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        showToastError(e.getMessage());
                        hideProgressDialog();
                    }
                } else {
                    showToastError(data);
                    hideProgressDialog();
                }
            }
        };
        CartExecute.getCart(this, callback, CustomerController.getCurrentCustomer(this).getEntity_id());
    }

    //////////////// setup paypal
    private void initPaypal() {
        config = new PayPalConfiguration()
                .rememberUser(true)
                .acceptCreditCards(true)
                .environment(CONFIG_ENVIRONMENT)
                .clientId(CONFIG_CLIENT_ID);
//                        // The following are only used in PayPalFuturePaymentActivity.
//                .merchantName("Example Merchant")
//                .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
//                .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));
    }

    private void checkoutByPaypal(float pay_price) {
        PayPalPayment thingToBuy = new PayPalPayment(new BigDecimal(pay_price), "SGD", "Shang", PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, com.paypal.android.sdk.payments.PaymentActivity.class);
        // send the same configuration for restart resiliency
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_PAYMENT, thingToBuy);
        startActivityForResult(intent, REQUEST_CODE_PAYMENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirm =
                        data.getParcelableExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                boolean isSuccess = false;
                if (confirm != null) {
                    try {
                        JSONObject jsonObject = confirm.toJSONObject();
                        JSONObject response = jsonObject.getJSONObject("response");
                        PAY_ID = response.getString("id");
                        isSuccess = true;
                        createOrder();
                    } catch (JSONException e) {
                        isSuccess = false;
                    }
                }
                if (isSuccess) {
//                    showToastOk("Check out successfully!" + "\n" + PAY_ID);
                    showToastOk("Check out successfully!");
                } else {
                    showToastError("Check out unsuccessfully!");
                }
            } else {
                showToastError("Check out unsuccessfully!");
            }
        }
    }
    //////////////// setup paypal
}
