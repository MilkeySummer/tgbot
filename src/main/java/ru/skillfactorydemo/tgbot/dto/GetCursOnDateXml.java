package ru.skillfactorydemo.tgbot.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;


@XmlRootElement(name = "GetCursOnDateXML", namespace = "http://web.cbr.ru/")
@XmlAccessorType(XmlAccessType.FIELD) //Указываем, как получить/записать значения в поля данного объекта
@Data //Геттеры и сеттеры
public class GetCursOnDateXml {

    @XmlElement(name = "On_date", required = true, namespace = "http://web.cbr.ru/") //Указание на то, в каком теге XML должно быть данное поле
    protected XMLGregorianCalendar onDate;
}
