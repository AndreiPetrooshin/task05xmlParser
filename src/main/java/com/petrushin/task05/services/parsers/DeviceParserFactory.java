package com.petrushin.task05.services.parsers;

import com.petrushin.task05.services.parsers.dom.DeviceDOMParser;
import com.petrushin.task05.services.parsers.jaxb.DeviceJAXBParser;
import com.petrushin.task05.services.parsers.sax.DeviceSAXParser;
import com.petrushin.task05.services.parsers.stax.DeviceStAXParser;

public class DeviceParserFactory {
    private enum TypeParser     {
        SAX, STAX, DOM, JAXB
    }
    public Parser getParserByType(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new DeviceDOMParser();
            case STAX:
                return new DeviceStAXParser();
            case SAX:
                return new DeviceSAXParser();
            case JAXB:
                return new DeviceJAXBParser();
            default:
                throw new EnumConstantNotPresentException (type.getDeclaringClass(), type.name());
        }
    }
}
