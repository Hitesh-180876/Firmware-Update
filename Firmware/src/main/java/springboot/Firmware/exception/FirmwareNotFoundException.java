package springboot.Firmware.exception;

public class FirmwareNotFoundException extends Exception{
    public FirmwareNotFoundException(int id){
        super("User id not found"+id);
    }
}
