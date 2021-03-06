import React from "react";
import { action } from "@storybook/addon-actions";
import { storiesOf } from "@storybook/react";
import { MemoryRouter } from "react-router-dom";

import Consent from "./Consent";

const header = {
  name: "Criminal Record Check"
};

const page = {
  header
};

const onContinueClick = action("onButtonContinueClicked");

storiesOf("Consent page", module)
  .add("Default", () => (
    <MemoryRouter>
      <Consent page={page} onContinueClick={onContinueClick} />
    </MemoryRouter>
  ))
  .addParameters({ viewport: { defaultViewport: "mobile2" } })
  .add("Mobile", () => (
    <MemoryRouter>
      <Consent page={page} onContinueClick={onContinueClick} />
    </MemoryRouter>
  ));
