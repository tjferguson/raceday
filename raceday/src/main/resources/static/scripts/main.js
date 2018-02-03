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
   
    if($("#bracket_container").length === 1) {
        setInterval(function() {
            window.location.reload(true);
          }, 10000);
    }   
});

function saveBracket(bracketId) {
    var roundId = $("#" + bracketId + "_roundId").val();
    var vech1Id = $("#" + bracketId + "_vech1Id").val();
    var vech2Id = $("#" + bracketId + "_vech2Id").val();
    var winnerId = $("#" + bracketId + "winnerId").val();
    var bracket = JSON.stringify(
            {
                "bracketId":bracketId,
                "roundId":roundId,
                "vech1Id":vech1Id,
                "vech2Id":vech2Id,
                "winnerId":winnerId
            }
    );
    
    $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "/manual/bracket",
                data : bracket,
                dataType : 'json',
                success : function(result) {
                    console.log(result);
                    //location.reload(true);
                },
                error : function(e) {
                    alert("Error Saving Bracket!");
                    console.log("ERROR: ", e);
                }
        });
}
