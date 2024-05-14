import { Routes } from '@angular/router';
import { SignupComponent } from './components/signup/signup.component';
import { LoginComponent } from './components/login/login.component';
import { ConsumidoresComponent } from './components/consumidores/consumidores.component';
import { HomeComponent } from './components/home/home.component';
import { OfertantesComponent } from './components/ofertantes/ofertantes.component';
import { HomeConsumidorComponent } from './components/home-consumidor/home-consumidor.component';
import { ConsumidorDataComponent } from './components/consumidor-data/consumidor-data.component';

export const routes: Routes = [
    {
        path: "sign-up",
        component: SignupComponent
    },
    {
        path: "login",
        component: LoginComponent
    },
    {
        path: "consumidores",
        component: ConsumidoresComponent
    },
    {
        path: "consumidores/home/:user",
        component: HomeConsumidorComponent
    },
    {
        path: "ofertantes",
        component: OfertantesComponent
    },
    {
        path: "",
        component: HomeComponent
    },
    {
        path: "consumidores/data/:id",
        component: ConsumidorDataComponent
    }
];
