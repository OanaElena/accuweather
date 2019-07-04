import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Region} from '../entity/region';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  constructor(private http: HttpClient) {
  }

  loadRegions(): Observable<Region[]> {
    return this.http.get<Region[]>('/server/regions');
  }
}
