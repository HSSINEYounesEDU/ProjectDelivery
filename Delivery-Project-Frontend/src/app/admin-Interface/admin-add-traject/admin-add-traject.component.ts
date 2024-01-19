import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpServiceService } from '../../services/http-service.service';
import { catchError, of, tap } from 'rxjs';

@Component({
  selector: 'app-admin-add-traject',
  templateUrl: './admin-add-traject.component.html',
  styleUrl: './admin-add-traject.component.css'
})
export class AdminAddTrajectComponent implements OnInit {

  page = 0;

  trajects: { id: number, source: string, destination: string, road: string[] }[] = [];
  displayedTrajects: { id: number, source: string, destination: string, road: string[] }[] = [];

  constructor(private router: Router, private httpService: HttpServiceService) { };

  // DRIVERS DISPLAY

  ngOnInit(): void {
    const url = "http://localhost:8080/traject-api/get-trajects/0/10";
    this.httpService.getRequest(url)
      .pipe(
        tap(response => {
          this.trajects = response.content;
          this.displayedTrajects = response.content;
        }),
        catchError(error => {
          console.error(error);
          return of([]);
        })
      )
      .subscribe();
  }

  onSearchSource(): void {
    if (this.source == "" && this.destination == "") {
      this.displayedTrajects = this.trajects;
    }
    else {
      this.displayedTrajects = this.trajects.filter(item =>
        item.source.toLowerCase().startsWith(this.source.toLowerCase())
      );
    }
  }
  onSearchDestination(): void {
    if (this.source == "" && this.destination == "") {
      this.displayedTrajects = this.trajects;
    }
    else {
      this.displayedTrajects = this.displayedTrajects.filter(item =>
        item.destination.toLowerCase().startsWith(this.destination.toLowerCase())
      );
    }
  }


  // TRAJECT CREATION

  cities = [
    "AD_DAKHLA",
    "AGADIR",
    "AGUELMOUS",
    "AIN_EL_AOUDA",
    "AIN_HARROUDA",
    "AIN_TAOUJDAT",
    "AIT_MELLOUL",
    "AIT_OURIR",
    "AL_AAROUI",
    "AL_ATTAWIA",
    "AL_FQIH_BEN_CALAH",
    "AL_HOCEIMA",
    "AZEMMOUR",
    "AZIYLAL",
    "AZROU",
    "BARRECHID",
    "BENI_MELLAL",
    "BEN_GUERIR",
    "BENI_YAKHLEF",
    "BERKANE",
    "BIOUGRA",
    "BIR_JDID",
    "BOUJAD",
    "BOU_ARFA",
    "BOUSKOURA",
    "BOUKNadel",
    "CASABLANCA",
    "CHICHAOUA",
    "DEMNAT",
    "EL_AIOUN",
    "EL_JADID",
    "EL_HAJEB",
    "EL_KELAA_DES_SRARHNA",
    "FES",
    "FNIDQ",
    "GUELMIM",
    "GUERCIF",
    "IHEDDADENE",
    "IMZOREN",
    "INEZGANE",
    "JERADA",
    "KENITRA",
    "KHENIFRA",
    "KHEMIS_SAHEL",
    "KSAR_EL_KEBIR",
    "KOURIBGA",
    "LAAYOUNE",
    "LARACHE",
    "MARRAKECH",
    "MARTIL",
    "MDIQ",
    "MECHRAA_BEL_KSIRI",
    "MEDIOUNA",
    "MEHDYA",
    "MEKNES",
    "MIDALT",
    "MISSOUR",
    "MOHAMMEDIA",
    "MOULAY_ABDALLAH",
    "MOULAY_BOUSSELHAM",
    "MRIRT",
    "MY_DRARGA",
    "NADOR",
    "OUED_ZEM",
    "OULAD_BARHIL",
    "OULAD_TAYEB",
    "OULAD_TEIMA",
    "OUED_ZEM",
    "OUJDA_ANGAD",
    "QASBAT_TADLA",
    "RABAT",
    "SAFI",
    "SALE",
    "SEFROU",
    "SETTAT",
    "SIDI_BENNOUR",
    "SIDI_QACEM",
    "SIDI_SLIMANE",
    "SIDI_SMAIIL",
    "SIDI_YAHIA_EL_GHARB",
    "SIDI_YAHYA_ZAER",
    "SKHIRATE",
    "SOUK_ET_TNINE_JORF_EL_MELLAH",
    "TAHLA",
    "TAOUIRT",
    "TAMESLOUHT",
    "TAZA",
    "TEMARA",
    "TEMISIA",
    "TETOUAN",
    "TIFARITI",
    "TIT_MELLIL",
    "YOUSSOUFIA",
    "ZAIO",
    "ZAGORA",
    "ZAWYAT_ECH_CHEIKH",
    "ZEGHANGHANE",
    "ZEKI"
  ];

  source = "";
  destination = "";
  newCity = "";
  road: string[] = [];



  onSubmit(): void {

    if (true) {
      const url = "http://localhost:8080/traject-api/create";
      this.road.push(this.destination)
      const request = {
        "id": 0,
        "source": this.source,
        "destination": this.destination,
        "road": this.road
      };

      console.log(request);

      this.httpService.postRequest(url, request).subscribe({
        next: (response) => {
          alert("Traject created successfully.");
          this.page = 0;
          this.ngOnInit();
        },
        error: (error) => {
          console.error(error);
        }
      });
    }
  }

  addCityToRoad(): void {
    this.road.push(this.newCity);
    this.newCity = "";
  }

}
