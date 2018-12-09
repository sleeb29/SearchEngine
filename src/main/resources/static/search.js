$(document).ready(function() {

    $('form').submit(function(event) {

        var searchTerms = $('input[id=search-query]').val();
        if (searchTerms.length === 0) {
            return;
        }
        var terms = searchTerms.split(/\S.*?\."?(?=\s|$)/g);
        console.log(terms);
        var formData = {
            searchTerms : terms
        };

        console.log(formData);

        var xhr = $.ajax({
            type 		: 'POST',
            url 		: 'api/search',
            data 		: JSON.stringify(formData),
            contentType:"application/json; charset=utf-8",
            dataType 	: 'json'
        }).done(function(data) {
                console.log(data);
                var searchResults = data.searchResults;
                var table = $('<table>').addClass('searchResults');
                for(var i = 0; i < searchResults.length; i++){
                    var row = $('<tr>').text(searchResults[i]);
                    table.append(row);
                }

                var existing = $('.results').find('table')
                console.log(existing);
                if(!existing.length){
                    $('.results').append(table);
                } else {
                    existing.replaceWith(table);
                }
            })
            .fail(function(data) {
                console.log(data);
            });

        console.log("xhr:" + JSON.stringify(xhr));
        event.preventDefault();
    });

});