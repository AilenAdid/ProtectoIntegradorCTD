import { useState, useEffect, useContext } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { registerLocale, setDefaultLocale } from "react-datepicker";
import "./CalendarReserve.css";
import es from 'date-fns/locale/es';
/*import SessionContextProvider from "../../context/sessionContext";*/
export default function CalendarReserve({ status, handleSelectRangeDate, idProduct, error }) {
    /*const { token } = useContext(SessionContextProvider);*/
    registerLocale('es', es)
    // const [startDate, setStartDate] = useState(null);
    const [arrayDaysReserve, setArrayDaysReserve] = useState([]);
    const [dateRange, setDateRange] = useState([null, null]);
    const [startDate, endDate] = dateRange;
    const onChange = (dates) => {
        if (status !== "disabled") {
            setDateRange(dates);
        }
    }
    function editNamesDaysWeek() {
        const namesDaysWeek = document.querySelectorAll(".react-datepicker__day-name");
        namesDaysWeek.forEach(name => {
            name.innerHTML = name.textContent.substring(0, 1).toUpperCase();
        })
    }

    function createArrayDaysReserve(arrayReserves) {
        let aux = [];
        arrayReserves.forEach(reserve => {
            let start = new Date(reserve.dateIn.split("-")[0], reserve.dateIn.split("-")[1] - 1, reserve.dateIn.split("-")[2]);
            let end = new Date(reserve.dateOut.split("-")[0], reserve.dateOut.split("-")[1] - 1, reserve.dateOut.split("-")[2]);
            for (let i = start; i <= end; i.setDate(i.getDate() + 1)) {
                aux.push(new Date(i));
            }
        })
        setArrayDaysReserve(aux);
    }
    useEffect(() => {
        if (idProduct !== undefined) {
            fetch(`http://localhost:8081/products/${idProduct}`)
                .then(res => res.json())
                .then(data => {
                    createArrayDaysReserve(data.availableDays);
                }).catch(error => console.log(error))
        }
    }, [idProduct])
    useEffect(() => {
        editNamesDaysWeek();
        let dateStart = new Date(startDate);
        let dateEnd = new Date(endDate);
        if (endDate !== null && startDate !== null) {
            handleSelectRangeDate(dateStart.toLocaleDateString(), dateEnd.toLocaleDateString());
        }
    }, [startDate, endDate])
    return (
        <div className="calendarReserve">
            {error && <p className="error">{error}</p>}
            <DatePicker
                inline
                locale="es"
                minDate={new Date()}
                excludeDates={arrayDaysReserve}
                calendarClassName={status}
                dateFormat="dd MMM."
                renderCustomHeader={({
                    monthDate,
                    customHeaderCount,
                    decreaseMonth,
                    increaseMonth,
                }) => (
                    <div>
                        <button
                            aria-label="Previous Month"
                            className={
                                "react-datepicker__navigation react-datepicker__navigation--previous"
                            }
                            style={customHeaderCount === 1 ? { visibility: "hidden" } : null}
                            onClick={decreaseMonth}
                        >
                            <span
                                className={
                                    "react-datepicker__navigation-icon react-datepicker__navigation-icon--previous"
                                }
                            >
                                {"<"}
                            </span>
                        </button>
                        <span className="react-datepicker__current-month">
                            {monthDate.toLocaleString("es-ES", {
                                month: "long",
                            }).substring(0, 1).toUpperCase() + monthDate.toLocaleString("es-ES", { month: "long" }).slice(1)}
                        </span>
                        <button
                            aria-label="Next Month"
                            className={
                                "react-datepicker__navigation react-datepicker__navigation--next"
                            }
                            style={customHeaderCount === 0 ? { visibility: "hidden" } : null}
                            onClick={increaseMonth}
                        >
                            <span
                                className={
                                    "react-datepicker__navigation-icon react-datepicker__navigation-icon--next"
                                }
                            >
                                {">"}
                            </span>
                        </button>
                    </div>
                )}
                startDate={startDate}
                endDate={endDate}
                selected={startDate}
                selectsRange={true}
                onChange={onChange}
                monthsShown={2}
            />

        </div>
    )
}