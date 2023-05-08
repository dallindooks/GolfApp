import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private _username!: String;
  private _jwt!: String;


  public get jwt(): String {
    return this._jwt;
  }
  public set jwt(value: String) {
    this._jwt = value;
  }

  public get username(): String {
    return this._username;
  }
  public set username(value: String) {
    this._username = value;
  }

  constructor() { }
}
