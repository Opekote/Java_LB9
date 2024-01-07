# API Data to Excel Converter

This Java application demonstrates how to interact with a web API, retrieve data from various endpoints, and store the data in Excel format using Java IO. Additionally, it includes JUnit tests for the conversion process.

## Classes and Methods

### 1. `DemoWorkWithAPI` Class
- **Main Method:** `main(String[] args)`
  - Takes user input for the resulting Excel file name.
  - Defines an array of `Endpoint` objects representing different API endpoints.
  - Retrieves data from each endpoint, converts the JSON response to Excel format, and saves it to the specified file.

### 2. `Endpoint` Class (Record)
- **Attributes:**
  - `path`: API endpoint path
  - `queryParams`: Query parameters for the endpoint
  - `jsonKey`: Key to extract data array from JSON response
  - `sheetName`: Name of the Excel sheet to be created

### 3. `JSONToXLSX` Class
- **Methods:**
  - `convert(String jsonInput, String filename, String dataArrayKey, String sheetName)`
    - Converts JSON data to Excel format and saves it to a file.
  - `getOrCreateWorkbook(String filename)`
    - Checks if the Excel file exists; if yes, opens it; if not, creates a new workbook.
  - `verifySheetDoesNotExist(Workbook workbook, String sheetName)`
    - Throws an exception if a sheet with the same name already exists in the workbook.
  - `extractDataArrayFromJson(String jsonInput, String dataArrayKey)`
    - Extracts the data array from the JSON response.
  - `createAndFillHeaderRow(Sheet sheet, JsonNode firstItem)`
    - Creates and fills the header row in the Excel sheet.
  - `populateSheetWithData(Sheet sheet, ArrayNode dataArray)`
    - Populates the Excel sheet with data.
  - `adjustColumnsWidth(Sheet sheet, int columnCount)`
    - Adjusts the column widths in the Excel sheet.
  - `writeFile(Workbook workbook, String filePath)`
    - Writes the workbook to an Excel file.

### 4. `JSONToXLSXTest` Class
- **Test Methods:**
  - `testConvertWithNewFile()`
    - Tests the conversion with a modified JSON content, checks the existence of the created Excel file, and validates the content.
  - `testConvertWithExistingSheetName()`
    - Tests the scenario where converting with an existing sheet name should throw an `IllegalArgumentException`.

### 5. `WorkWithAPI` Class
- **Methods:**
  - `doGetRequest(String endpointURL)`
    - Sends a GET request to the specified API endpoint and returns the JSON response.
  - `buildUrl(String path, String queryOptions)`
    - Builds the complete URL for the API request.
  - `getData(String endpointPath, String queryParams)`
    - Retrieves data from the API using the specified endpoint path and query parameters.

### 6. `WorkWithAPITest` Class
- **Test Methods:**
  - `testBuildUrl()`
    - Tests the `buildUrl` method to ensure correct URL construction.


