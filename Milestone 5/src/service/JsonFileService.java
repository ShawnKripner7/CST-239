package service;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import exception.FileServiceException;
import product.SalableProduct;

/**
 * Provides JSON file input and output services
 * for store inventory.
 *
 * @author Shawn Kripner
 */
public class JsonFileService implements FileService {

    private ObjectMapper objectMapper;

    /**
     * Creates a new JSON file service.
     */
    public JsonFileService() {
        objectMapper = new ObjectMapper();
    }

    /**
     * Reads salable products from a JSON file.
     *
     * @param filename the file to read
     * @return the products read from the file
     * @throws FileServiceException if the file cannot be read
     */
    @Override
    public SalableProduct[] readProducts(String filename)
            throws FileServiceException {

        try {

            File file = new File(filename);

            return objectMapper.readValue(
                    file,
                    SalableProduct[].class);

        } catch (IOException e) {

            throw new FileServiceException(
                    "Unable to read inventory file: "
                            + filename,
                    e);
        }
    }

    /**
     * Writes salable products to a JSON file.
     *
     * @param filename the file to write
     * @param products the products to save
     * @throws FileServiceException if the file cannot be written
     */
    @Override
    public void writeProducts(
            String filename,
            SalableProduct[] products)
            throws FileServiceException {

        try {

            File file = new File(filename);

            objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(file, products);

        } catch (IOException e) {

            throw new FileServiceException(
                    "Unable to write inventory file: "
                            + filename,
                    e);
        }
    }
}