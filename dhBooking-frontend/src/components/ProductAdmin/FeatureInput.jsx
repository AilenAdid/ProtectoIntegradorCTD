import { useState, useContext, useEffect } from "react";
import { DataContext } from "../../Context/DataProvider";
import { types } from "../../Context/dataReducer";
import { findFeatureIcon } from "../Features/Features";
import { FeatureInputWrapper, UlIconList, Iconprinted, ButtonAdd,InputWrapper,LabelImage } from "./FeatureInput.styles";
import { Input, Select } from "../Administration/AdministrationStyle";

const FeatureInput = () => {
    const [isIconsListActive, setIsIconsListActive] = useState(false)
    const [state, dispatch] = useContext(DataContext)    
    const [characteristics, setCharacteristics] = useState([])
    const {newProductData} = state
  const [iconSelected, setIconSelected] = useState("")  

  useEffect(() => {
    const request = async () => {
      try {
        const fetching = await fetch(`http://localhost:8081/characteristics`);
        const response = await fetching.json();
        console.log(response)
        setCharacteristics(response);
      } catch (err) {
        // Handle the error here
      }
    };
    request();
  }, []);
  
  
    
    const saveFeatureHandler = (e)=>{
      e.preventDefault()     
      console.log(JSON.parse(iconSelected))
      //setFeaturesToSend(iconSelected)
      dispatch({
        type: types.addFeature,
        payload: JSON.parse(iconSelected)
      })
      setIconSelected("")
    } 
   

  return (
    <FeatureInputWrapper>
      <InputWrapper>
        <LabelImage>
          <Select
            name="name" 
            placeholder="Selecciona un atributo" 
            type="text" 
            autoComplete="on" 
            //value={iconSelected?.title ? iconSelected?.title : ''}             
            onChange={(choice) => {setIconSelected(choice.target.value) ;setIsIconsListActive(!isIconsListActive)}}          
            onClick={()=> setIsIconsListActive(!isIconsListActive)}
          >
          {characteristics?.map(characteristic => (
            <option key={characteristic.id} value={JSON.stringify(characteristic)}>{characteristic.title}</option>
          ))}
          </Select>
        </LabelImage>
        
      </InputWrapper>
      
      <ButtonAdd onClick={(e)=>saveFeatureHandler(e)}>+</ButtonAdd>
    </FeatureInputWrapper>
  );
};

export default FeatureInput