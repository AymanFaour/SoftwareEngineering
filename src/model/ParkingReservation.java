package model;


public class ParkingReservation {
	
	private String  _carNumber;
	private String  _lotName;
	private String _name;
	private String  _start;
	private String _end;
	private String cost;
	
	public ParkingReservation(String _carNumber, String _lotName, String _name, String _start, String _end,
			String cost) {
		super();
		this._carNumber = _carNumber;
		this._lotName = _lotName;
		this._name = _name;
		this._start = _start;
		this._end = _end;
		this.cost = cost;
	}

	public String get_carNumber() {
		return _carNumber;
	}

	public void set_carNumber(String _carNumber) {
		this._carNumber = _carNumber;
	}

	public String get_lotName() {
		return _lotName;
	}

	public void set_lotName(String _lotName) {
		this._lotName = _lotName;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_start() {
		return _start;
	}

	public void set_start(String _start) {
		this._start = _start;
	}

	public String get_end() {
		return _end;
	}

	public void set_end(String _end) {
		this._end = _end;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
	
	
	
		
}
