import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscriber } from 'rxjs';
import { ClientesService } from 'src/app/clientes.service';
import { Cliente } from '../cliente';

@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {

  cliente: Cliente;
  success: boolean = false;
  errors: any = []; 
  id: any;

  constructor(
    private service: ClientesService,
    private router: Router,
    private activatedRoute: ActivatedRoute

    ) { 
      
    this.cliente = new Cliente();
    this.cliente = service.getCliente().subscribe( response => {
      this.success = true;
      this.errors = null;
    }, errorResponse => {
      this.errors = ["Erro ao atualizar o cliente."]
    });
  }

  ngOnInit(): void {
    let params = this.activatedRoute.params
    params.subscribe( urlParams => {
      this.id = urlParams["id"];
      this.service.getClienteById(this.id).subscribe(
        response => this.cliente = response, 
        errorResponse => this.cliente = new Cliente())

    })
    
    
  }

  onSubmit(){
    if(this.id){
      this.service.atualizar(this.cliente)
    } else{

    
    this.service.
    salvar(this.cliente)
    .subscribe(response =>{
      this.success = true;
      this.errors = [];
      this.cliente = response;
    } , errorResponse => {
      this.errors = errorResponse.error.errors
      
    });
  }
}

  voltarParaListagem(){
    this.router.navigate(["/clientes-lista"])
  }

}
