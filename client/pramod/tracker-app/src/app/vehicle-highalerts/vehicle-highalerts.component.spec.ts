import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleHighAlertsComponent } from './vehicle-highalerts.component';

describe('VehicleHighAlertsComponent', () => {
  let component: VehicleHighAlertsComponent;
  let fixture: ComponentFixture<VehicleHighAlertsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehicleHighAlertsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicleHighAlertsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
