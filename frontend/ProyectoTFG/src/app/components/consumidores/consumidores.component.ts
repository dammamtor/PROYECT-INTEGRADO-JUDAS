import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { ConsumidoresService } from '../../services/consumidores.service';
import { Router } from '@angular/router';
import { Consumidores } from '../../models/Consumidores';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-consumidores',
  standalone: true,
  imports: [NavbarComponent, CommonModule],
  templateUrl: './consumidores.component.html',
  styleUrl: './consumidores.component.css'
})
export class ConsumidoresComponent {
  consumidores: Consumidores[] = [];

  constructor(private ruta: Router, private consumidorService: ConsumidoresService) { }

  ngOnInit(): void {
    this.listarConsumidores();
  }

  listarConsumidores(): void {
    this.consumidorService.listarConsumidores()
      .subscribe(consumidores => {
        this.consumidores = consumidores;
        console.log(consumidores);
      });
  }
}
