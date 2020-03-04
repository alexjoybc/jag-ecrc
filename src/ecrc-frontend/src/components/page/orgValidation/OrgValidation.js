import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import { Redirect } from "react-router-dom";
import axios from "axios";
import Header from "../../base/header/Header";
import Footer from "../../base/footer/Footer";
import OrgValidationText from "../../base/orgValidationText/OrgValidationText";
import "../page.css";
import SideCards from "../../composite/sideCards/SideCards";
import { isAuthenticated } from "../../../modules/AuthenticationHelper";

const jwt = require("jsonwebtoken");

export default function OrgValidation({ page: { header, setOrg } }) {
  const [orgTicketNumber, setOrgTicketNumber] = useState("");
  const [orgError, setOrgError] = useState("");
  const [toTransition, setToTransition] = useState(false);
  const [toOrgVerification, setToOrgVerification] = useState(false);

  let token = jwt.sign({ foo: "bar" }, "shhhhh", { expiresIn: "1m" });

  sessionStorage.setItem("jwt", token);

  isAuthenticated();

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  const orgValidation = () => {
    axios
      .get(
        `/ecrc/doAuthenticateUser?orgTicketId=${orgTicketNumber}&token=${token}`
      )
      .then(res => {
        setOrg(res.data.accessCodeResponse);
        setToOrgVerification(true);
      })
      .catch(error => {
        if (error.response.status === 404) {
          setOrgError("Please enter a valid org code");
        } else if (error.response.status === 401) {
          setToTransition(true);
        }
      });
  };

  const textInput = {
    label: "Access code",
    id: "orgId",
    textInputStyle: "placeHolder",
    isRequired: true,
    errorMsg: orgError
  };

  const button = {
    label: "Validate",
    buttonStyle: "btn btn-primary",
    buttonSize: "btn btn-sm",
    type: "submit"
  };

  if (toOrgVerification) {
    return <Redirect to="/ecrc/orgverification" />;
  }

  if (toTransition) {
    return <Redirect to="/ecrc/transition" />;
  }

  return (
    <main>
      <Header header={header} />
      <div className="page">
        <div className="content col-md-8">
          <OrgValidationText
            textInput={textInput}
            onChange={setOrgTicketNumber}
            button={button}
            onClick={orgValidation}
          />
        </div>
        <div className="sidecard">
          <SideCards type={"accesscode"} />
          <SideCards type={"criminalrecord"} />
        </div>
      </div>
      <Footer />
    </main>
  );
}

OrgValidation.propTypes = {
  page: PropTypes.shape({
    setOrg: PropTypes.func.isRequired,
    header: PropTypes.shape({
      name: PropTypes.string.isRequired
    }).isRequired
  }).isRequired
};
