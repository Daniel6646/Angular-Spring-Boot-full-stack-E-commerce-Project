import { Injector, NgModule } from '@angular/core';
import {  RouterModule, Routes,Router } from '@angular/router';
import { ProductListComponent } from './components/product-list/product-list.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { CartDetailsComponent } from './components/cart-details/cart-details.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { OktaAuthGuard, OktaCallbackComponent } from '@okta/okta-angular';
import { LoginComponent } from './components/login/login.component';
import { MembersPageComponent } from './components/members-page/members-page.component';
import { OktaAuth } from '@okta/okta-auth-js';
import { OrderHistoryComponent } from './components/order-history/order-history.component';


export const routes: Routes = [
  
  // {path: 'members', component: MembersPageComponent, canActivate : [OktaAuthGuard], 
  // data : {onAuthRequired : sendToLoginPage} },
  
  {path: 'members', component: MembersPageComponent, canActivate: [OktaAuthGuard],
  data: {onAuthRequired: sendToLoginPage} },

  // [OktaAuthGuard] is a route guard , if authenticated we give acess to this route or send user to the login page
  //senToLoginPage is our custom function requrired to send to login page
  
  {path: 'order-history', component: OrderHistoryComponent, canActivate: [OktaAuthGuard],
  data: {onAuthRequired: sendToLoginPage} },//canactivate makes sure no one can acess this link without being authenticated
  
  {path: 'login/callback', component: OktaCallbackComponent}, //once user is authentctd, this will redirect to our app,.normally you would need to parse response & stre OAuth + OIDC token, okta call back component does that for us
  {path: 'login', component: LoginComponent},
  {path: 'checkout', component: CheckoutComponent},
  {path: 'cart-details', component: CartDetailsComponent},
  {path: 'products/:id', component: ProductDetailsComponent},
  {path: 'search/:keyword', component: ProductListComponent},
  {path :'category/:id' ,component: ProductListComponent},
  {path :'category' ,component: ProductListComponent},
  {path :'products' ,component: ProductListComponent},
  {path :'' , redirectTo:'/products', pathMatch  : 'full'}, // if no path is there then redirect so some page
  {path :'**' ,redirectTo : '/products', pathMatch  : 'full'},

//  ** is generic wildward it link does not match anything it will send to products its like else if nothing matches and executes in if statement it will execute else this is else

];

function sendToLoginPage(oktaAuth : OktaAuth, injector : Injector) {
  // use injector to access any service within your application, router in our case
  const router = injector.get(Router); //get router then reidrect to custom login page
  
  //Redirect user to custom login page
  router.navigate(['/login']);

  }

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})




export class AppRoutingModule { }
