import { Component, OnInit } from '@angular/core';
import { Product } from '../../common/product';
import { ProductService } from '../../services/product.service';
import { ActivatedRoute } from '@angular/router';
import { CartService } from '../../services/cart.service';
import { CartItem } from '../../common/cart-item';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent implements OnInit {

  product!: Product;

  constructor(private productService: ProductService,
    private route: ActivatedRoute,
    private cartService : CartService) { }


  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.handleProductDetails();
    })
  }


  handleProductDetails(): void {

    // get id param string convert string into number using "+" symbol

    const theProductId: number = +this.route.snapshot.paramMap.get('id')!;
    console.log("handleproductdetails this.route.snapshot::" + this.route.snapshot.paramMap.get('id'));

    console.log("handleproductdetails" + theProductId);
    this.productService.getProduct(theProductId).subscribe(
      data => {
        this.product = data;

      })
  }


  addToCart(){

    const cartItem = new CartItem(this.product);
    this.cartService.addToCart(cartItem);

  }

}
