import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { Route, RouterModule, Routes } from '@angular/router';
import { ProductCategoryMenuComponent } from './components/product-category-menu/product-category-menu.component';
import { SearchComponent } from './components/search/search.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CartStatusComponent } from './components/cart-status/cart-status.component';
import { CartDetailsComponent } from './components/cart-details/cart-details.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { LoginStatusComponent } from './components/login-status/login-status.component';

import {
  OktaCallbackComponent,OKTA_CONFIG, OktaAuthModule 
} from '@okta/okta-angular'

import { OktaAuth } from '@okta/okta-auth-js'

import myAppConfig from './config/my-app-config'; //okta config constant which is ref to myappconfig.odic
import { ProductService } from './services/product.service';
import { MembersPageComponent } from './components/members-page/members-page.component';
import { OrderHistoryComponent } from './components/order-history/order-history.component';
import { AuthInterceptorService } from './services/auth-interceptor.service';

const oktaConfig  = myAppConfig.oidc;
const oktaAuth = new OktaAuth(oktaConfig);



@NgModule({
  declarations: [
    AppComponent,
    ProductCategoryMenuComponent,
    SearchComponent,
    ProductDetailsComponent,
    CartStatusComponent,
    CartDetailsComponent,
    CheckoutComponent,
    LoginComponent,
    LoginStatusComponent,
    MembersPageComponent,
    OrderHistoryComponent
    //user made components declare here
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    //RouterModule.forRoot(routes),
    ProductListComponent,
    HttpClientModule,
    RouterModule,
    NgbModule,
    ReactiveFormsModule,
    OktaAuthModule
    
 ],
  providers: [
    provideClientHydration(), ProductService ,{provide : OKTA_CONFIG, useValue : {oktaAuth}}, 
    {provide : HTTP_INTERCEPTORS, useClass : AuthInterceptorService, multi : true} //useclass means use our class AuthIntrcptrService for interceptors
    //will have 0 to many interceptors hence multi true
    //provide : HTTP_INTERCEPTORS token to http interceptors
    //useClass : AuthInterceptorService Register our made class aas http interceptor
    //multi : true inform angular tht HTTP_NTERCEPTOR is token for injection of an array of values
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
