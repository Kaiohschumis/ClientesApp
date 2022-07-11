import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username!: string
  password!: string
  loginError!: boolean;
  cadastro!: boolean;

  constructor(
    private router: Router
  ) { }
  ngOnInit(): void {
   
  }

  onSubmit(){
    this.router.navigate(["/home"])
  }

  preparaCadastrar(){
    this.cadastro = true;
  }

  cancelaCadastro(){
    this.cadastro = false;
  }

}
