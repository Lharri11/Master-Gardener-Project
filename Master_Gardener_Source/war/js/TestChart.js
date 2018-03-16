<canvas id="invoice_status_chart" min-height="300" max-height="500"></canvas>
<script>
var invoice_status_data = {};
function invoice_status_chart() {
    var can = jQuery('#invoice_status_chart');
    var ctx = can.get(0).getContext("2d");
    var container = can.parent().parent(); // get width from proper parent
    var $container = jQuery(container);
    can.attr('width', $container.width()); //max width
    can.attr('height', $container.height()); //max height
    var chart = new Chart(ctx).Doughnut(invoice_status_data);
}
var invoice_status_data = function () {
    jQuery.post( ajaxurl, {
            action: 'callback_id',
            data: 'invoice_statuses',
            security: 'nonce'
        },
        function( data ) {
            invoice_status_data = [
                {
                    value: data.status_temp,
                    color:"#26292C",
                    highlight: "#363B3F",
                    label: "Temp"
                },
                {
                    value: data.status_pending,
                    color:"#FFA500",
                    highlight: "#FAD694",
                    label: "Pending"
                },
                {
                    value: data.status_partial,
                    color:"#E14D43",
                    highlight: "#FF5A5E",
                    label: "Partial"
                },
                {
                    value: data.status_complete,
                    color: "#76AB48",
                    highlight: "#86BC4A",
                    label: "Complete"
                }
            ];
            invoice_status_chart();
        }
    );
};
jQuery(document).ready(function($) {
    jQuery(window).resize(invoice_status_chart);
    invoice_status_data();
})
</script>