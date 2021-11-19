package hu.uni.miskolc.teszteles.beadando.dao;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import hu.uni.miskolc.teszteles.beadando.exceptions.EmployeeAlreadyExistsException;
import hu.uni.miskolc.teszteles.beadando.exceptions.EmployeeDoesNotExistException;
import hu.uni.miskolc.teszteles.beadando.model.Employee;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.Collection;



public class EmployeeDaoImpl implements EmployeeDao{

    private MongoCollection<EmployeePojo> collection;

    public EmployeeDaoImpl(String uri, String database, String collection) {
        ConnectionString connection = new ConnectionString(uri);
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry  = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connection).codecRegistry(codecRegistry).build();

        MongoClient client = MongoClients.create(clientSettings);
        MongoDatabase db = client.getDatabase(database);
        this.collection = db.getCollection(collection, EmployeePojo.class);
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return collection.find().map(EmployeePojoConverter::pojoToEmployee).into(new ArrayList());
    }

    @Override
    public Employee getEmployeeById(int id) throws EmployeeDoesNotExistException {
        Employee employee = collection.find(Filters.eq("_id", id)).map(EmployeePojoConverter::pojoToEmployee).first();
        if(employee == null)
            throw new EmployeeDoesNotExistException();
        return employee;
    }

    @Override
    public void createEmployee(Employee employee) throws EmployeeAlreadyExistsException {
        try {
            collection.insertOne(EmployeePojoConverter.employeeToPojo(employee));
        } catch (MongoWriteException e) {
            throw new EmployeeAlreadyExistsException();
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws EmployeeDoesNotExistException {
        UpdateResult result = collection.replaceOne(Filters.eq("_id", employee.getId()), EmployeePojoConverter.employeeToPojo(employee));
        if(result.getModifiedCount() == 0)
            throw new EmployeeDoesNotExistException();

    }

    @Override
    public void deleteEmployee(Employee employee) throws EmployeeDoesNotExistException {
        DeleteResult result = collection.deleteOne(Filters.eq("_id", employee.getId()));
        if(result.getDeletedCount() == 0)
            throw new EmployeeDoesNotExistException();
    }

    @Override
    public void deleteAllEmployees(){
        collection.deleteMany(new BasicDBObject());
    }
}
