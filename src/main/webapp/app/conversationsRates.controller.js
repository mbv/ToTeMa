angular
    .module('demo')
    .controller('ConversationsRateListController', ConversationsRateListController);

function ConversationsRateListController($controller) {
    var self = this;
    $controller('EntityListController', {resourceName: 'convertion-rates', self: self});
}
