$().ready(function() {
    $(".vechSave").on("click", function() {
        $.ajax({
                    type : "POST",
                    contentType : "application/json",
                    url : "/savebracket",
                    data : JSON.stringify({"winner":$(this).val(), "bracketId":$("#bracketId").val()}),
                    dataType : 'json',
                    success : function(result) {
                        console.log(result);
                        location.reload(true);
                    },
                    error : function(e) {
                        alert("Error Saving Bracket!");
                        console.log("ERROR: ", e);
                    }
            });
    });
});