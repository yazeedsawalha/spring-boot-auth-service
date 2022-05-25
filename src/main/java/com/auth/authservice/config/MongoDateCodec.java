package com.auth.authservice.config;

import org.bson.BsonWriter;
import org.bson.codecs.DateCodec;
import org.bson.codecs.EncoderContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MongoDateCodec extends DateCodec {
    private final SimpleDateFormat formatter;

    public MongoDateCodec(String format) {
        formatter = new SimpleDateFormat(format);
    }

    @Override
    public void encode(final BsonWriter writer, final Date value, final EncoderContext encoderContext) {
        writer.writeString(formatter.format(value));
    }
}