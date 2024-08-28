package bookstore.bookstoreapi_13.mapper;

import bookstore.bookstoreapi_13.dto.CustomerDTO;
import bookstore.bookstoreapi_13.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO toDTO(Customer customer);

    Customer toEntity(CustomerDTO customerDTO);
}
