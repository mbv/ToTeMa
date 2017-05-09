angular
    .module('app')
    .controller('ReportsController', ReportsController);

function ReportsController(DocumentDownloaderService, $state) {
    var self = this;

    self.downloadCountry = downloadCountry;
    self.downloadEmployee = downloadEmployee;
    self.downloadOffice = downloadOffice;
    self.downloadProduct = downloadProduct;
    self.downloadProductDetailed = downloadProductDetailed;
    self.downloadYear = downloadYear;

    function downloadCountry(type) {
        DocumentDownloaderService.downloadDocument('api/reports/countries', type);
    }
    function downloadEmployee(type) {
        DocumentDownloaderService.downloadDocument('api/reports/employees', type);
    }
    function downloadOffice(type) {
        DocumentDownloaderService.downloadDocument('api/reports/offices', type);
    }
    function downloadProduct(type) {
        DocumentDownloaderService.downloadDocument('api/reports/products', type);
    }
    function downloadProductDetailed(type) {
        DocumentDownloaderService.downloadDocument('api/reports/products-detailed', type);
    }
    function downloadYear(type) {
        DocumentDownloaderService.downloadDocument('api/reports/years', type);
    }
}