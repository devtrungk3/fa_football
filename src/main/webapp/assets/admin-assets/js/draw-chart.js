Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

function number_format(number, decimals, dec_point, thousands_sep) {
    // *     example: number_format(1234.56, 2, ',', ' ');
    // *     return: '1 234,56'
    number = (number + '').replace(',', '').replace(' ', '');
    var n = !isFinite(+number) ? 0 : +number,
        prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
        sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
        dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
        s = '',
        toFixedFix = function(n, prec) {
            var k = Math.pow(10, prec);
            return '' + Math.round(n * k) / k;
        };
    // Fix for IE parseFloat(0.55).toFixed(0) = 0;
    s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
    if (s[0].length > 3) {
        s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
    }
    if ((s[1] || '').length < prec) {
        s[1] = s[1] || '';
        s[1] += new Array(prec - s[1].length + 1).join('0');
    }
    return s.join(dec);
}

function draw_role_percentage_chart(roles, percentages) {

    var ctx = document.getElementById("myPieChart2").getContext('2d');
    var myPieChart = new Chart(ctx, {
        type: 'doughnut',  // or 'doughnut' if you prefer
        data: {
            labels: roles,  // Ensure roles is defined in your script
            datasets: [{
                data: percentages,  // Ensure percentages is defined in your script
                backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc'],  // Background colors for each segment
                hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],  // Hover background colors
                hoverBorderColor: "rgba(234, 236, 244, 1)",  // Border color on hover
            }]
        },
        options: {
            maintainAspectRatio: false,  // Maintain the aspect ratio
            tooltips: {
                backgroundColor: "rgb(255,255,255)",  // Tooltip background color
                bodyFontColor: "#858796",  // Tooltip font color
                borderColor: '#dddfeb',  // Tooltip border color
                borderWidth: 1,  // Tooltip border width
                xPadding: 15,  // Tooltip x padding
                yPadding: 15,  // Tooltip y padding
                displayColors: false,  // Do not display colors in tooltip
                // caretPadding: 10,  // Padding around the caret
            },
            legend: {
                display: true,  // Show legend
            },
            plugins: {
                datalabels: {
                    color: '#FFF',  // Color of the labels
                    formatter: (value, ctx) => {
                        // Convert raw value to percentage
                        let total = ctx.chart.data.datasets[0].data.reduce((a, b) => a + b, 0);
                        let percentage = ((value / total) * 100).toFixed(2) + '%';
                        return percentage;  // Display as percentage
                    },
                    font: {
                        weight: 'bold',  // Bold font
                        size: 14  // Font size
                    },
                    className: 'custom-chart-label'
                }
            }
        },
    });
}

function draw_match_count(date, matchCount, scope, id) {

    var unit = 'day';  // Giá trị mặc định là 'day'
    if (scope == 'day') {
        unit = 'day';
    } else if (scope == 'week') {
        unit = 'week';
    } else if (scope == 'month') {
        unit = 'month';
    }

    var ctx = document.getElementById(id).getContext('2d');
    var myBarChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: date,
            datasets: [{
                label: "Số trận",
                backgroundColor: "#4e73df",
                hoverBackgroundColor: "#2e59d9",
                borderColor: "#4e73df",
                data: matchCount,
            }],
        },
        options: {
            plugins: {
                datalabels: {
                    anchor: 'end',  // Position of the label relative to the bar (end of the bar)
                    align: 'top',  // Align the label at the top of the bar
                    color: '#444',  // Label text color
                    font: {
                        weight: 'bold',  // Label font weight
                        size: 14  // Label font size
                    },
                    formatter: function(value, context) {
                        return value;  // Display the raw value on top of each bar
                    }
                },
            },
            maintainAspectRatio: false,
            layout: {
                padding: {
                    left: 10,
                    right: 25,
                    top: 25,
                    bottom: 0
                }
            },
            scales: {
                xAxes: [{
                    unit: 'time',
                    time: {
                        unit: unit,  // Display the date in 'day', 'week', 'month', or 'year' unit
                    },
                    gridLines: {
                        display: false,
                        drawBorder: false
                    },
                    maxBarThickness: 25,
                }],
                yAxes: [{
                    ticks: {
                        min: 0,
                        // max: maxValue,
                        // maxTicksLimit: 5,
                        padding: 10,
                        // Include a dollar sign in the ticks

                    },
                    gridLines: {
                        color: "rgb(234, 236, 244)",
                        zeroLineColor: "rgb(234, 236, 244)",
                        drawBorder: false,
                        borderDash: [2],
                        zeroLineBorderDash: [2]
                    }
                }],
            },
            legend: {
                display: false
            },
            tooltips: {
                titleMarginBottom: 10,
                titleFontColor: '#6e707e',
                titleFontSize: 14,
                backgroundColor: "rgb(255,255,255)",
                bodyFontColor: "#858796",
                borderColor: '#dddfeb',
                borderWidth: 1,
                xPadding: 15,
                yPadding: 15,
                displayColors: false,
                caretPadding: 10,

            },
        }
    });
}

