import { useState,useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";
import Calendar from "../SpecificProduct/Calendar";
import { Place, CheckCircleOutline, Error } from "@styled-icons/material-rounded";
import Rate from "../Rate/Rate";
import { Form, FieldsetData, FieldsetSummary,CalendarWrapper, Time, ImageWrapper, SummaryWrapper, CheckInDiv, CheckOutDiv, Button, TimeWrapper, InfoDates, ErrorDates, ErrorTime, ErrorRangeDates, ErrorBooking} from "./BookingForm.styles";
import { Data, Card } from "./RegistrationFormStyle";
import { DataContext } from "../../Context/DataProvider";
import {Buffer} from 'buffer';
import { types } from "../../Context/dataReducer";



const BookingForm = ({blockedRangeDates,isNotAllowedDates, userName,userSurname,UserEmail, imageUrl, imageTitle, category, title, place, checkIn, checkOut, idProduct}) => {
  
    const navigate = useNavigate()
    const [city, setCity] = useState('')
    const [errorDates, setErrorDates] = useState(false)
    const [formDatesError, setFormDatesError] = useState(false)
    const [formTimeError, setFormTimeError] = useState(false)
    const [errorBooking, setErrorBooking] = useState(false)
    const [time, setTime] = useState("")     
    
    const [state, dispatch] = useContext(DataContext)
    const {userData} = state


    const handleCity = (e) =>{
        setCity(e.target.value);
    }

    const handleTime=(e)=>{
      setTime(e.target.value)
      if(e.target.value !== ""){
        setFormTimeError(false)
      }        
    }

    useEffect(()=>{
      if(checkIn === "Fecha inválida" || checkOut === "Fecha inválida"){
        setErrorDates(true)
      }
      else{
        setErrorDates(false)
        setFormDatesError(false)
      }
      
    },[checkIn,checkOut,errorDates])

    const handleSubmit =(e) =>{      
      e.preventDefault()
      if(errorDates){setFormDatesError(true)}      
      if(!formDatesError){setErrorDates(false)}    
      if(time === ""){
        setFormTimeError(true)
      }       
      
      if(!errorDates && !formDatesError && time !== "" && !isNotAllowedDates){  
          const dataBooking ={
            startDate : checkIn,
            endDate : checkOut,            
            product : {id: idProduct},
            user : {id: userData.id},
            startTime : time
          }

          console.log(dataBooking)

          fetch('http://localhost:8081/reservations',{
            method: 'POST',
            headers: new Headers({
              
              'Content-Type' : 'application/json',
              'Accept' : 'application/json',          
              'Authorization' : userData.token,              
            }),
            body: JSON.stringify(dataBooking)
          })
          .then(response => {
            console.log(response.status)
            switch(response.status){
              case 201:                
                navigate("/success");  
                dispatch({
                  type: types.dateRange,
                  payload:   {
                    startDate: "",
                    endDate: ""
                 }
                })              
                break;
              default:
                setErrorBooking(true)
                break;                
            }
            response.text()
            console.log(response);
          })
          
          .then(data => console.log(data)) 

          sessionStorage.removeItem("checkIn")
          sessionStorage.removeItem("checkOut")
          localStorage.removeItem("rangeDates")
          
      }  
    }

  return (
    <Form onSubmit={(e)=>handleSubmit(e)}>
      <FieldsetData>
        <Data>
          <h2>Tus datos</h2> 
            <Card>
              <div>
                <label htmlFor="name">Nombre</label>
                <input
                  disabled
                  type="text"
                  name="name"
                  value={userData.name}
                ></input>
              </div>
              <div>
                <label htmlFor="surname">Apellido</label>
                <input
                  disabled
                  type="text"
                  name="surname"
                  value={userData.lastname}
                ></input>
              </div>
              <div>
                <label htmlFor="email">Correo electrónico</label>
                <input
                  disabled
                  type="email"
                  name="email"
                  value={userData.email}
                ></input>
              </div>
            </Card>         
        </Data>
        <CalendarWrapper>
          <h2>Seleccioná tu fecha de reserva</h2>
          
          <Calendar            
            isInteractiveCalendar={true}
            rangeDates={blockedRangeDates}                       
          />
          {isNotAllowedDates ? <ErrorRangeDates><Error/>Error, el rango de fechas no debe contener días no disponibles</ErrorRangeDates> : null}
        </CalendarWrapper>
        <Time>
          <h2>Tu horario de llegada</h2>          
          <TimeWrapper>
            <div>
                <CheckCircleOutline/>
                <p>Tu habitación va a estar lista para el check-in entre las 15:00 hs. y las 0:00 hs.</p>
            </div>
            <label htmlFor="time">Indicá tu horario estimado de llegada </label>
            <select name="time" value={time? time : "default"} onChange={(e)=> handleTime(e)} required>                
                <option value="default" disabled>Seleccionar hora de llegada</option>
                <option value="15:00:00">15:00 hs.</option>
                <option value="15:30:00">15:30 hs.</option>
                <option value="16:00:00">16:00 hs.</option>
                <option value="16:30:00">16:30 hs.</option>
                <option value="17:00:00">17:00 hs.</option>
                <option value="17:30:00">17:30 hs.</option>
                <option value="18:00:00">18:00 hs.</option>
                <option value="18:30:00">18:30 hs.</option>
                <option value="19:00:00">19:00 hs.</option>
                <option value="19:30:00">19:30 hs.</option>
                <option value="20:00:00">20:00 hs.</option>
                <option value="20:30:00">20:30 hs.</option>
                <option value="21:00:00">21:00 hs.</option>
                <option value="21:30:00">21:30 hs.</option>
                <option value="22:00:00">22:00 hs.</option>
                <option value="22:30:00">22:30 hs.</option>
                <option value="23:00:00">23:00 hs.</option>
                <option value="23:30:00">23:30 hs.</option>
                <option value="00:00:00">0:00 hs.</option>
            </select>
            {formTimeError && <ErrorTime><Error/>Debes seleccionar una hora</ErrorTime>}
          </TimeWrapper>
        </Time>
      </FieldsetData>
      <FieldsetSummary>
        <h2>Detalle de la reserva</h2>
        <ImageWrapper>
          <img src={imageUrl} alt={imageTitle}></img>
        </ImageWrapper>
        <SummaryWrapper>
          <div>
            <p>{category}</p>
            <h3>{title}</h3>
            <Rate />
          </div>
          <div>
            <Place />
            <p>{place}</p>
          </div>
          <CheckInDiv>
            <label htmlFor="check_in">Check In</label>
            <input name="check_in" readOnly  defaultValue={checkIn} type="date" />
          </CheckInDiv>
          <CheckOutDiv>
            <label htmlFor="check_out">Check Out</label>
            <input name="check_out" readOnly  defaultValue={checkOut} type="date"/>
          </CheckOutDiv>
          {formDatesError  && <ErrorDates><Error/>Error, debes seleccionar un rango de fechas</ErrorDates>}
          {errorDates && !formDatesError?  <InfoDates><Error/>Selecciona un rango de fechas</InfoDates>: null}
          {errorBooking && <ErrorBooking><Error/>Lamentablemente la reserva no ha podido realizarse. Por favor, intente más tarde</ErrorBooking>}
          <Button type="submit">Confirmar reserva</Button>
        </SummaryWrapper>
      </FieldsetSummary>
    </Form>
  );
};

export default BookingForm;
