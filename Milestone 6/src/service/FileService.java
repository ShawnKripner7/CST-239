package service;

import exception.FileServiceException;
import product.SalableProduct;

/**
 * Defines file operations used to save and load
 * store inventory.
 *
 * @author Shawn Kripner
 */
public interface FileService {

    /**
     * Reads products from a file.
     *
     * @param filename the file to read
     * @return array of salable products
     * @throws FileServiceException if the file cannot be read
     */
    SalableProduct[] readProducts(String filename)
            throws FileServiceException;

    /**
     * Writes products to a file.
     *
     * @param filename the file to write
     * @param products the products to save
     * @throws FileServiceException if the file cannot be written
     */
    void writeProducts(
            String filename,
            SalableProduct[] products)
            throws FileServiceException;
}