import {Injectable} from '@angular/core';
import {StorageProvider} from "./storage.provider";

const TOKEN_LOCAL_STORAGE_KEY = "Jwt-Token";

@Injectable({
  providedIn: 'root'
})
export class TokenProvider {

  constructor(private storageProvider: StorageProvider) {
  }

  public getToken(): string {
    return this.storageProvider.getItem(TOKEN_LOCAL_STORAGE_KEY);
  }

  public saveToken(token: string): void {
    this.storageProvider.saveItem(TOKEN_LOCAL_STORAGE_KEY, token);
  }


  public removeToken(): void {
    this.storageProvider.removeItem(TOKEN_LOCAL_STORAGE_KEY);
  }
}
