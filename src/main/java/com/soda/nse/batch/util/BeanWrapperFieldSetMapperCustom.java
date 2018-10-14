package com.soda.nse.batch.util;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.util.StringUtils;
import org.springframework.validation.DataBinder;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BeanWrapperFieldSetMapperCustom<T> extends BeanWrapperFieldSetMapper<T> {

    @Override
    protected void initBinder(DataBinder binder) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MMM-uuuu");
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (!StringUtils.isEmpty(text)) {
                    LocalDate localDate = LocalDate.parse(text, dateFormat);
                    setValue(localDate);
                } else {
                    setValue(null);
                }
            }

            @Override
            public String getAsText() throws IllegalArgumentException {
                Object date = getValue();
                if (date != null) {
                    return dateFormat.format((LocalDate) getValue());
                } else {
                    return "";
                }
            }
        });
    }
}
