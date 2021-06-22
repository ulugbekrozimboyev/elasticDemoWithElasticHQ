package uz.elasticdemo.with.elastic.hq.document.bootstrap;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import uz.elasticdemo.with.elastic.hq.document.Employee;
import uz.elasticdemo.with.elastic.hq.repository.EmployeeRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Ulug'bek Ro'zimboyev  <ulugbekrozimboyev@gmail.com>
 * Date: 6/22/2021 10:03 PM
 */
public class SampleDataSet {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleDataSet.class);
    private static final String INDEX_NAME = "sample";
    private static final String INDEX_TYPE = "employee";

    @Autowired
    EmployeeRepository repository;
    @Autowired
    ElasticsearchTemplate template;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 10000; i++) {
            bulk(i);
        }
    }

    public void bulk(int ii) {
        try {
            if (!template.indexExists(INDEX_NAME)) {
                template.createIndex(INDEX_NAME);
            }
            ObjectMapper mapper = new ObjectMapper();
            List queries = new ArrayList();
            List employees = employees();
            for (Employee employee : employees) {
                IndexQuery indexQuery = new IndexQuery();
                indexQuery.setId(employee.getId().toString());
                indexQuery.setSource(mapper.writeValueAsString(employee));
                indexQuery.setIndexName(INDEX_NAME);
                indexQuery.setType(INDEX_TYPE);
                queries.add(indexQuery);
            }
            if (queries.size() > 0) {
                template.bulkIndex(queries);
            }
            template.refresh(INDEX_NAME);
            LOGGER.info("BulkIndex completed: {}", ii);
        } catch (Exception e) {
            LOGGER.error("Error bulk index", e);
        }
    }
}
