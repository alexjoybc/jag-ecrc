import React from "react";
import { create } from "react-test-renderer";
import { MemoryRouter } from "react-router-dom";

import ApplicationForm from "./ApplicationForm";

describe("ApplicationForm Component", () => {
  test("Matches the snapshot", () => {
    const header = {
      name: "Criminal Record Check"
    };

    const applicant = {
      firstName: "Robert",
      middleName: "Norman",
      lastName: "Ross",
      birthPlace: "",
      birthDate: "1942-10-29",
      sex: "Male",
      bcDLNumber: "",
      phoneNumber: "",
      emailAddress: "",
      street: "123 Somewhere",
      city: "Here",
      province: "British Columbia",
      postalCode: "V9V 9V9",
      country: "Canada",
      applicantPosition: "",
      organizationFacility: ""
    };

    const setApplicant = () => {};
    const org = {
      defaultScheduleTypeCd: "WBSD"
    };

    const page = {
      header,
      applicant,
      org,
      setApplicant
    };

    const applicationPage = create(
      <MemoryRouter>
        <ApplicationForm page={page} />
      </MemoryRouter>
    );
    expect(applicationPage.toJSON()).toMatchSnapshot();
  });
});