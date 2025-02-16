package com.example.semesterproject.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.semesterproject.R;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductDetailsActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView priceTextView, productDescription;
    private Button addToCartBtn;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // Initialize UI elements
        productImage = findViewById(R.id.productImage);
        priceTextView = findViewById(R.id.priceTextView);
        productDescription = findViewById(R.id.productDescription);
        addToCartBtn = findViewById(R.id.addToCartBtn);

        // Initialize Volley request queue
        requestQueue = Volley.newRequestQueue(this);

        // Fetch product details
        int productId = getIntent().getIntExtra("PRODUCT_ID", -1);
        if (productId != -1) {
            fetchProductDetails(productId);
        }

        // Handle Add to Cart action
        addToCartBtn.setOnClickListener(v -> {
            // Handle add to cart functionality
            // Example: Show a toast for now
            Toast.makeText(this, "Product added to cart", Toast.LENGTH_SHORT).show();
        });
    }

    private void fetchProductDetails(int productId) {
        // URL to fetch product details from the FakeStore API
        String url = "https://fakestoreapi.com/products/" + productId;

        // Create a JSON request to fetch the product details
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parse the response and update the UI
                            String title = response.getString("title");
                            String description = response.getString("description");
                            double price = response.getDouble("price");
                            String image = response.getString("image");

                            updateUI(title, description, price, image);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ProductDetailsActivity.this, "Error parsing product details", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Toast.makeText(ProductDetailsActivity.this, "Network error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the request queue
        requestQueue.add(jsonObjectRequest);
    }

    private void updateUI(String title, String description, double price, String imageUrl) {
        // Set the product image
        Glide.with(this)
                .load(imageUrl)
                .into(productImage);

        // Set the product price
        priceTextView.setText("$" + price);

        // Set the product description
        productDescription.setText(description);
    }
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
