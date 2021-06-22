package uz.elasticdemo.with.elastic.hq.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Author: Ulug'bek Ro'zimboyev  <ulugbekrozimboyev@gmail.com>
 * Date: 6/22/2021 10:00 PM
 */
@Document(indexName = "employees")
@Data
public class Employee {

    @Id
    private Long id;
    @Field(type = FieldType.Object)
    private Organization organization;
    @Field(type = FieldType.Object)
    private Department department;
    private String name;
    private int age;
    private String position;
}
