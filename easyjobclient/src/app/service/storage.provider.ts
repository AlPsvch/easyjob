import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StorageProvider {

  private storage: any;

  constructor() {
    this.storage = window.localStorage;
  }

  public saveItem(key: string, value: string): void {
    this.storage.setItem(key, value);
  }

  public getItem(key: string): string {
    return this.storage.getItem(key);
  }

  public removeItem(key: string): void {
    this.storage.removeItem(key);
  }
}
