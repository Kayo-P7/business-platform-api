package com.Vy.telegram_bot.controller;

import com.Vy.telegram_bot.dto.active.ProductActiveRequest;
import com.Vy.telegram_bot.dto.active.ProductActiveResponse;
import com.Vy.telegram_bot.dto.Request.ProductRequest;
import com.Vy.telegram_bot.dto.Response.ProductResponse;
import com.Vy.telegram_bot.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Tag(
        name = "Products",
        description = "Operations related to product management"
)
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(
            summary = "Create a new product",
            description = "Create product in inventory"
    )
    @ApiResponses({

            @ApiResponse(
                    responseCode = "201",
                    description = "Product created successfully"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Product already exists"
            )
    })
    @PostMapping
    public ResponseEntity<ProductResponse> save(@Valid @RequestBody ProductRequest request) {


        ProductResponse productResponse = productService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);


    }

    @Operation(
            summary = "Find product by ID",
            description = "Returns a product using its UUID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Product found successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProductId(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @Operation(
            summary = "List all products",
            description = "Returns a paginated list of products"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Product retrieved successfully"
            )
    })
    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    //deletar, ByName, findStock, findProductPrice, findProductActive
    @Operation(
            summary = "Delete a product",
            description = "Deletes a product by ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Product deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProductById(@PathVariable("id") UUID id) {

        productService.deletarProduct(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Find product by name",
            description = "Returns a product using its String name"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Product find successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found"
            )
    })
    @GetMapping("/name/{name}")
    public ResponseEntity<ProductResponse> findByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(productService.findByName(name));
    }
    @Operation(
            summary = "Find products with low stock",
            description = "Returns products whose stock is below the specified value"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Products retrieved successfully"
            )
    })
    @GetMapping("/stocklow/{stock}")
    public ResponseEntity<List<ProductResponse>> findLowStock(@PathVariable("stock") Integer stock) {
        return ResponseEntity.ok(productService.findLowStock(stock));
    }
    @Operation(
            summary = "Find products by price range",
            description = "Returns products within the specified minimum and maximum price"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Products retrieved successfully"
            )
    })
    @GetMapping("/price/{min}/{max}")
    public ResponseEntity<List<ProductResponse>> findProductByPrice(@PathVariable("min") BigDecimal min, @PathVariable("max") BigDecimal max) {

        return ResponseEntity.ok(productService.findProductsByPrice(min, max));
    }
    @Operation(
            summary = "Find products by active status",
            description = "Returns products filtered by their active status"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Products retrieved successfully"
            )
    })
    @GetMapping("/active/{active}")
    public ResponseEntity<List<ProductResponse>> findActiveProducts(@PathVariable("active") Boolean active) {
        return ResponseEntity.ok(productService.findActiveProducts(active));
    }

    @Operation(
            summary = "Update a product",
            description = "Updates an existing product"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Product updated successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") UUID id, @Valid @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.updateProduct(id, productRequest);
        return ResponseEntity.ok(productResponse);
    }

    @Operation(
            summary = "List delete all products",
            description = "Delete all products from list"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "All products deleted successfully"
            )
    })
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAll() {
        productService.deleteAll();
        return ResponseEntity.noContent().build();
    }
    @Operation(
            summary = "Update product active status",
            description = "Updates the active status of a product"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Product active status updated successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request payload"
            )
    })
    @PatchMapping("/{id}/active")
    public ResponseEntity<ProductActiveResponse> updateActive(@PathVariable("id") UUID id, @Valid @RequestBody ProductActiveRequest request) {
        return ResponseEntity.ok().body(productService.updateActive(id, request));
    }
}

