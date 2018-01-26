package model;

import java.util.Calendar;
import java.util.HashMap;


public class ParkingLot {
	private String _name;
	private int _depth;
	private int _height;
	private int _width;
	private final int _capacity;
	private ParkingSlot[][][] _lot;
	private int _emptySlots;
	private int _reservedSlots;
	private int _usedSlots;
	private int _disabledSlots;

	private HashMap<String, ParkingPosition> _hash; // list of parked cars

	public ParkingLot(String _name, int _depth, int _height, int _width) {

		super();
		this._name = _name;
		this._depth = _depth;
		this._height = _height;
		this._width = _width;

		_lot = new ParkingSlot[_height][_width][_depth];

		for (int i = 0; i < this._depth; i++) {
			for (int j = 0; j < this._width; j++) {
				for (int k = 0; k < this._height; k++) {
					_lot[k][j][i] = new ParkingSlot();
				}
			}
		}

		_emptySlots = _depth * _width * _height;
		_capacity = _emptySlots;
		_reservedSlots = 0;
		_usedSlots = 0;
		_disabledSlots = 0;

		_hash = new HashMap<>();

	}

	public void changeEmptySlots(int change) {
		_emptySlots = _emptySlots + change;
	}

	public void changeReservedSlots(int change) {
		_reservedSlots = _reservedSlots + change;
	}

	public void changeUsedSlots(int change) {
		_usedSlots = _usedSlots + change;
	}

	public void changeDisableSlots(int change) {
		_disabledSlots = _disabledSlots + change;
	}

	public int getEmptySlots() {
		return _emptySlots;
	}

	public int getReservedSlots() {
		return _reservedSlots;
	}

	public int getUsedSlots() {
		return _usedSlots;
	}

	public int getDisableSlots() {
		return _disabledSlots;
	}

	public int getDepth() {
		return _depth;
	}

	public void setDepth(int _depth) {
		this._depth = _depth;
	}

	public int getHeight() {
		return _height;
	}

	public void setHeight(int _height) {
		this._height = _height;
	}

	public int getWidth() {
		return _width;
	}

	public void setWidth(int _width) {
		this._width = _width;
	}

	public ParkingSlot[][][] get_lot() {
		return _lot;
	}

	public void setLot(ParkingSlot[][][] _lot) {
		this._lot = _lot;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public ParkingPosition getEmptyParkingPosition() {
		for (int i = 0; i < this._depth; i++) {
			for (int j = 0; j < this._width; j++) {
				for (int k = 0; k < this._height; k++) {
					// System.out.println(_lot[k][j][i] + " "+ i + j + k);
					if (_lot[k][j][i].getStatus() == SpotStatus.Available) {
						return new ParkingPosition(i, j, k);
					}
				}
			}
		}
		return null;
	}

	public boolean InsertCar(String carNumber, Calendar arrive, Calendar leave) {

		ParkingPosition pos;
		if (!this.isFull()) {
			if (_hash.containsKey(carNumber)) {
				return false;
			} else {
				pos = getEmptyParkingPosition();
				int x = pos.x;
				int y = pos.y;
				int z = pos.z;

				_lot[x][y][z].setCarNumber(carNumber);

				if (_lot[x][y][z].getStatus() == SpotStatus.Reserved) {
					_reservedSlots--;
				} else if (_lot[x][y][z].getStatus() == SpotStatus.Available) {
					_emptySlots--;
				}
				_lot[x][y][z].setStatus(SpotStatus.Busy);

				_lot[x][y][z].setArrive(arrive);
				_lot[x][y][z].setLeave(leave);

				_usedSlots++;

				_hash.put(carNumber, new ParkingPosition(x, y, z));

				return true;
			}
		} else {
			return false;
		}

	}

	public boolean isFull() {
		return (_disabledSlots + _reservedSlots + _usedSlots == _capacity);
	}

	public boolean ExtractCar(String carNumber) {
		if (_hash.containsKey(carNumber)) {
			ParkingPosition pos = _hash.get(carNumber);
			_lot[pos.x][pos.y][pos.z].setCarNumber("");
			_lot[pos.x][pos.y][pos.z].setStatus(SpotStatus.Available);
			_lot[pos.x][pos.y][pos.z].setArrive(null);
			_lot[pos.x][pos.y][pos.z].setLeave(null);

			_usedSlots--;
			_emptySlots++;

			_hash.remove(carNumber);

			return true;
		} else {
			return false;
		}
	}

	public ParkingPosition getPosition(String carNumber) {
		return _hash.get(carNumber);

	}

	public boolean CanPark(int numOfReserves) {

		int parkingLen = _hash.size();
		int total = parkingLen + numOfReserves + _disabledSlots;
		return total < _capacity;

	}

	public boolean CanReserve(int numOfReserves) {

		int total = numOfReserves + _disabledSlots;
		return total < _capacity;

	}
	
	public boolean CanDisapled(){
		return (_usedSlots + _disabledSlots) < _capacity;
	}
	
	public boolean CanUnDisapled(){
		return (_disabledSlots) > 0;
	}
	
	public boolean IsBusy(int hight, int width, int depth){
		return _lot[hight][width][depth].getStatus() == SpotStatus.Busy;
	}
	
	public boolean IsAvailable(int hight, int width, int depth){
		return _lot[hight][width][depth].getStatus() == SpotStatus.Available;
	}
	
	public boolean IsDisapled(int hight, int width, int depth){
		return _lot[hight][width][depth].getStatus() == SpotStatus.Unavailable;
	}
	
	public boolean disaplySlot(int hight, int width, int depth) {
		
		_lot[hight][width][depth].setCarNumber("");
		_lot[hight][width][depth].setStatus(SpotStatus.Unavailable);
		_lot[hight][width][depth].setArrive(null);
		_lot[hight][width][depth].setLeave(null);
		
		_disabledSlots++;
		_emptySlots--;
		
		return true;
		
	}
	
	public boolean undisaplySlot(int hight, int width, int depth) {
		
		_lot[hight][width][depth].setCarNumber("");
		_lot[hight][width][depth].setStatus(SpotStatus.Available);
		_lot[hight][width][depth].setArrive(null);
		_lot[hight][width][depth].setLeave(null);
		
		_disabledSlots--;
		_emptySlots++;
		
		return true;
		
	}

}
