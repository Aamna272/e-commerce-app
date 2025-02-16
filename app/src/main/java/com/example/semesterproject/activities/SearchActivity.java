package com.example.semesterproject.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.semesterproject.R;
import com.example.semesterproject.activities.adapters.ProductAdapter;
import com.example.semesterproject.activities.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private ArrayList<Product> productList, filteredList;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.productList);

        // Use GridLayoutManager for 2-column grid layout
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));  // 2 columns

        productList = new ArrayList<>();
        filteredList = new ArrayList<>();

        productAdapter = new ProductAdapter(this, filteredList);
        recyclerView.setAdapter(productAdapter);

        requestQueue = Volley.newRequestQueue(this);

        // Fetch the search query from the MainActivity
        String searchQuery = getIntent().getStringExtra("search_query");

        if (searchQuery != null && !TextUtils.isEmpty(searchQuery)) {
            // Fetch the products if search query is available
            fetchProducts(searchQuery);
        }
    }

    private void fetchProducts(String searchQuery) {
        String url = "https://fakestoreapi.com/products?limit=20";  // Fetch data from API

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        productList.clear();
                        filteredList.clear();

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

                        // Filter products based on the search query
                        filterProducts(searchQuery.toLowerCase());

                        productAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Log.e("SearchActivity", "JSON Parsing error", e);
                    }
                },
                error -> Log.e("SearchActivity", "API request error", error));

        requestQueue.add(request);
    }

    private void filterProducts(String query) {
        filteredList.clear();
        for (Product product : productList) {
            if (product.getTitle().toLowerCase().contains(query)) {
                filteredList.add(product);
            }
        }
        productAdapter.notifyDataSetChanged();
    }
}
