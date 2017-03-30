angular
    .module('demo')
    .controller('EntityListController', EntityListController);

function EntityListController($resource, $uibModal, resourceName, self, toaster) {
    self.resourceName = resourceName;
    self.EntityService = $resource('api/' + self.resourceName + '/:id',
        {
            id: '@id'
        },
        {
            update: {
                method: 'PUT'
            }

        });

    self.entities = [];
    self.isUpdatingChosen = false;

    self.addEntity = addEntity;
    self.deleteEntity = deleteEntity;
    self.editEntity = editEntity;

    self.toaster = toaster;
    self.promiseErrorHandler = function (errorResponse) {
        self.toaster.pop('error', "Error", errorResponse.data.error);
    };

    refresh();

    function refresh() {
        self.EntityService.query().$promise.then(function (result) {
            console.log('An success:');
            self.entities = result;
        }, self.promiseErrorHandler);
    }

    function addEntity() {
        openModal();
    }

    function deleteEntity(entity) {
        self.EntityService.delete(entity, refresh).$promise.then(null, self.promiseErrorHandler);
    }

    function editEntity(entity) {
        openModal(entity);
    }

    function openModal(entity) {
        var modalInstance = $uibModal.open({
            templateUrl: self.resourceName + 'Modal.html',
            controller: 'EntityModalController as self',
            resolve: {
                entity: function () {
                    return angular.copy(entity);
                },
                EntityService: function () {
                    return self.EntityService
                }
            }
        });
        modalInstance.result.then(refresh);
    }
}


