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
import com.example.semesterproject.activities.adapters.CategoryAdapter;
import com.example.semesterproject.activities.adapters.ProductAdapter;
import com.example.semesterproject.activities.Product;
import com.example.semesterproject.activities.Category;
import com.mancj.materialsearchbar.MaterialSearchBar;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView productRecyclerView;
    ProductAdapter productAdapter;
    ArrayList<Product> productList;
    RequestQueue requestQueue;
    RecyclerView recyclerView;
    RecyclerView categoryRecyclerView;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categoryList;

    private MaterialSearchBar searchBar;

    private static final String CATEGORY_API_URL = "https://fakestoreapi.com/products/categories";
    private static final String API_URL = "https://fakestoreapi.com/products";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBar = findViewById(R.id.searchBar);

        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                // Handle search state changes if needed
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                // Pass the search query to the SearchActivity
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("search_query", text.toString());  // Pass search query
                startActivity(intent);
            }

            @Override
            public void onButtonClicked(int buttonCode) {
                // Handle any additional button clicks (voice search, etc.)
            }
        });

        productRecyclerView = findViewById(R.id.productRecyclerView);
        productList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        productAdapter = new ProductAdapter(this, productList);
        productRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        productRecyclerView.setAdapter(productAdapter);

        fetchProducts();
//
//        recyclerView = findViewById(R.id.productList);
//        productList = new ArrayList<>();
//        productAdapter = new ProductAdapter(this, productList);
//        requestQueue = Volley.newRequestQueue(this);
//
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(productAdapter);
//
//        fetchProducts();


        categoryRecyclerView = findViewById(R.id.categoriesList);
        categoryList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(this, categoryList);
        requestQueue = Volley.newRequestQueue(this);

        categoryRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        categoryRecyclerView.setAdapter(categoryAdapter);

        fetchCategories();
    }

    private void fetchProducts() {
        String url = "https://fakestoreapi.com/products?limit=20";

        Log.d("FetchProducts", "Fetching data from: " + url);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        productList.clear(); // Clear previous data

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject productObj = response.getJSONObject(i);

                            String title = productObj.optString("title", "No title");
                            String image = productObj.optString("image", "");
                            String category = productObj.optString("category", "Unknown");
                            double price = productObj.optDouble("price", 0.0);

                            Log.d("FetchProducts", "Parsed Product: " + title);

                            // Only add if the title and image are valid
                            if (!title.isEmpty() && !image.isEmpty()) {
                                productList.add(new Product(title, image, category, price));
                            }
                        }
                        productAdapter.notifyDataSetChanged();
                        Log.d("FetchProducts", "Products updated in adapter. Total: " + productList.size());

                    } catch (JSONException e) {
                        Log.e("FetchProducts", "JSON Parsing error", e);
                    }
                },
                error -> {
                    if (error.networkResponse != null) {
                        Log.e("FetchProducts", "API Error: " + error.networkResponse.statusCode);
                    }
                    Log.e("FetchProducts", "API Request Failed", error);
                });

        // Ensure requestQueue is initialized
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(this);
        }

        requestQueue.add(request);
    }


    private void fetchCategories() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, CATEGORY_API_URL, null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            categoryList.add(new Category(response.getString(i)));
                        }
                        categoryAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Log.e("MainActivity", "JSON Parsing error", e);
                    }
                },
                error -> Log.e("MainActivity", "API request error", error)
        );

        requestQueue.add(jsonArrayRequest);
    }
}
