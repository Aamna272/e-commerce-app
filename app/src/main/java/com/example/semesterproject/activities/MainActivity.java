package com.example.semesterproject.activities;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.semesterproject.activities.adapters.CategoryAdapter;
import com.example.semesterproject.activities.adapters.ProductAdapter;
import com.example.semesterproject.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categories;
    ProductAdapter productAdapter;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initCategories();
        initProducts();
    }
    void initCategories(){
        categories=new ArrayList<>();
        categories.add(new Category("sports & outdoor " ,"https://i.pinimg.com/736x/1f/f4/29/1ff4297c2f6d6ab2a1e7f0475bbe9cd8.jpg","#FF6F61","some description",1));
        categories.add(new Category("sports & outdoor " ,"https://i.pinimg.com/736x/1f/f4/29/1ff4297c2f6d6ab2a1e7f0475bbe9cd8.jpg","#FF6F61","some description",1));
        categories.add(new Category("sports & outdoor " ,"https://i.pinimg.com/736x/1f/f4/29/1ff4297c2f6d6ab2a1e7f0475bbe9cd8.jpg","#FF6F61","some description",1));
        categories.add(new Category("sports & outdoor " ,"https://i.pinimg.com/736x/1f/f4/29/1ff4297c2f6d6ab2a1e7f0475bbe9cd8.jpg","#FF6F61","some description",1));
        categories.add(new Category("sports & outdoor " ,"https://i.pinimg.com/736x/1f/f4/29/1ff4297c2f6d6ab2a1e7f0475bbe9cd8.jpg","#FF6F61","some description",1));

        categoryAdapter=new CategoryAdapter(this,categories);


        GridLayoutManager layoutManager=new GridLayoutManager(this,4);
        binding.categoriesList.setLayoutManager(layoutManager);
        binding.categoriesList.setAdapter(categoryAdapter);
    }
    void initProducts(){
        products=new ArrayList<>();
        products.add(new Product("Women Cardigan","https://i.pinimg.com/736x/c7/cc/34/c7cc344ebece7ea78a783ab95c69765a.jpg","available",12,12,1,1));
        products.add(new Product("Women Cardigan","https://i.pinimg.com/736x/c7/cc/34/c7cc344ebece7ea78a783ab95c69765a.jpg","available",12,12,1,1));
        products.add(new Product("Women Cardigan","https://i.pinimg.com/736x/c7/cc/34/c7cc344ebece7ea78a783ab95c69765a.jpg","available",12,12,1,1));
        products.add(new Product("Women Cardigan","https://i.pinimg.com/736x/c7/cc/34/c7cc344ebece7ea78a783ab95c69765a.jpg","available",12,12,1,1));
        products.add(new Product("Women Cardigan","https://i.pinimg.com/736x/c7/cc/34/c7cc344ebece7ea78a783ab95c69765a.jpg","available",12,12,1,1));
        products.add(new Product("Women Cardigan","https://i.pinimg.com/736x/c7/cc/34/c7cc344ebece7ea78a783ab95c69765a.jpg","available",12,12,1,1));
        products.add(new Product("Women Cardigan","https://i.pinimg.com/736x/c7/cc/34/c7cc344ebece7ea78a783ab95c69765a.jpg","available",12,12,1,1));

        productAdapter = new ProductAdapter(this, products);

        GridLayoutManager layoutManager= new GridLayoutManager(this,2);
        binding.productList.setLayoutManager(layoutManager);
        binding.productList.setAdapter(productAdapter);
    }
}