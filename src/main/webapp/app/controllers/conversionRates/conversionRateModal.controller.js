angular
    .module('app')
    .controller('ConversionRateModalController', ConversionRateModalController);

function ConversionRateModalController($uibModalInstance, ConversionRateFactory, conversionRate) {
    var self = this;

    self.conversionRate = conversionRate;
    self.saveConversionRate = saveConversionRate;
    self.cancel = cancel;
    self.updateMode = !!self.conversionRate;


    function saveConversionRate() {
        if (self.updateMode){
            ConversionRateFactory.update(self.conversionRate, $uibModalInstance.close);
        } else {
            ConversionRateFactory.save(self.conversionRate, $uibModalInstance.close);
        }
    }

    function cancel() {
        $uibModalInstance.dismiss('cancel');
    }
}