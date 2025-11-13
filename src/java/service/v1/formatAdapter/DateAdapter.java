package service.v1.formatAdapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateAdapter extends XmlAdapter<String, Date> {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss");
    static{
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // evita el desfase
    }

    @Override
    public String marshal(Date v) throws Exception {
        return dateFormat.format(v);

    }

    @Override
    public Date unmarshal(String v) throws Exception {
        return dateFormat.parse(v);
    }

}