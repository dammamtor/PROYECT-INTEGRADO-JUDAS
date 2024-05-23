import { Routes } from '@angular/router';
import { SignupComponent } from './components/signup/signup.component';
import { LoginComponent } from './components/login/login.component';
import { ConsumidoresComponent } from './components/consumidores/consumidores.component';
import { HomeComponent } from './components/home/home.component';
import { OfertantesComponent } from './components/ofertantes/ofertantes.component';
import { HomeConsumidorComponent } from './components/home-consumidor/home-consumidor.component';
import { ConsumidorDataComponent } from './components/consumidor-data/consumidor-data.component';
import { ActividadesComponent } from './components/actividades/actividades.component';
import { SolicitudesComponent } from './components/solicitudes/solicitudes.component';
import { HomeOfertanteComponent } from './components/home-ofertante/home-ofertante.component';
import { OfertanteDataComponent } from './components/ofertante-data/ofertante-data.component';
import { AceptarSolicitudesComponent } from './components/aceptar-solicitudes/aceptar-solicitudes.component';
import { ListaActividadesOfertanteComponent } from './components/lista-actividades-ofertante/lista-actividades-ofertante.component';
import { CrearActividadComponent } from './components/crear-actividad/crear-actividad.component';

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
        path: "actividades",
        component: ActividadesComponent
    },
    {
        path: "ofertantes/lista-actividades-ofertante",
        component: ListaActividadesOfertanteComponent
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
        path: "ofertantes/home/:user",
        component: HomeOfertanteComponent
    },
    {
        path: "",
        component: HomeComponent
    },
    {
        path: "consumidores/data/:id",
        component: ConsumidorDataComponent
    },
    {
        path: "ofertantes/data/:id",
        component: OfertanteDataComponent
    },
    {
        path: "solicitud-actividad/:idConsumidor/:idActividad",
        component: SolicitudesComponent
    },
    {
        path: "aceptar-solicitud/:ofertanteId/:idSolicitud",
        component: AceptarSolicitudesComponent
    },
    {
        path: "ofertantes/:idOfertante/crear-actividad",
        component: CrearActividadComponent
    }
];
