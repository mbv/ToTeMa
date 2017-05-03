angular
    .module('app')
    .service('ErrorsService', ErrorsService);

function ErrorsService(toaster) {

    var self = {
        showErrors: showErrors,
        toaster: toaster
    };

    return self;

    function showErrors(errors) {
        console.log(errors);
        errors.forEach(function (error) {
            self.toaster.pop('error', toNormalForm(error.title), error.message, 10000);
        });
    }

    function toNormalForm(input) {
        return input.replace(/([A-Z])/g, ' $1')
            .replace(/^./, function (str) {
                return str.toUpperCase();
            });
    }
}