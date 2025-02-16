package com.example.semesterproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.semesterproject.R;
import com.example.semesterproject.activities.adapters.ProductAdapter;
import com.example.semesterproject.activities.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    RecyclerView productRecyclerView;
    ProductAdapter productAdapter;
    ArrayList<Product> productList;
    RequestQueue requestQueue;
    String categoryName;
    String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        productRecyclerView = findViewById(R.id.productList);
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productList);

        productRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        productRecyclerView.setAdapter(productAdapter);

        // Get category name from intent
        Intent intent = getIntent();
        categoryName = intent.getStringExtra("categoryName");

        // Fetch products by category
        fetchProductsByCategory();

        requestQueue = Volley.newRequestQueue(this);

        //selectedCategory = getIntent().getStringExtra("categoryName");

//        getSupportActionBar().setTitle(selectedCategory);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        productRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        productRecyclerView.setAdapter(productAdapter);

        fetchProductsByCategory();
    }

    private void fetchProductsByCategory() {
        String url = "https://fakestoreapi.com/products/category/" + categoryName.replace(" ", "%20");

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject productObj = response.getJSONObject(i);

                            Product product = new Product(
                                    productObj.getString("title"),
                                    productObj.getString("image"),
                                    productObj.getString("category"),
                                    productObj.getDouble("price")
                            );

                            productList.add(product);
                        }
                        productAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        Log.e("ProductsActivity", "JSON Parsing error", e);
                    }
                },
                error -> Log.e("ProductsActivity", "API request error", error));

        requestQueue.add(request);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
