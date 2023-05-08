import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { UserService } from '../services/userservice/user.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username!: String;
  password!: String;

  constructor(private http: HttpClient, private userService: UserService) { }

  login(): void {
    const userData = {
      username: this.username,
      password: this.password
    }

      this.http.post<String>(environment.baseApiUrlUser + "users/login", userData).subscribe((res: String) => {
        this.userService.jwt = res;
        this.userService.username = userData.username;
        console.log(this.userService.jwt);

      }, (error) => {
        console.log("Invalid credentials")
      });

  }


}
