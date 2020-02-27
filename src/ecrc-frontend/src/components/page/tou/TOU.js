import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import { Redirect } from "react-router-dom";

import Header from "../../base/header/Header";
import Footer from "../../base/footer/Footer";
import TermsOfUse from "../../base/termsOfUse/TermsOfUse";
import "../page.css";

export default function TOU({ page: { header } }) {
  const [toBCSCRedirect, setToBCSCRedirect] = useState(false);
  const [secondBoxChecked, setSecondBoxChecked] = useState(false);
  const [firstBoxChecked, setFirstBoxChecked] = useState(false);
  const [continueBtnEnabled, setContinueBtnEnabled] = useState(false);
  const [reachedEnd, setReachedEnd] = useState(false);

  useEffect(() => {
    if (firstBoxChecked && secondBoxChecked && reachedEnd) {
      setContinueBtnEnabled(true);
    } else {
      setContinueBtnEnabled(false);
    }
  }, [firstBoxChecked, secondBoxChecked, reachedEnd]);

  const onContinueClick = () => {
    setToBCSCRedirect(true);
  };

  if (toBCSCRedirect) {
    return <Redirect to="/ecrc/bcscRedirect" />;
  }
  const termOfUseOnScroll = event => {
    const { scrollHeight, scrollTop, clientHeight } = event.target;
    if (!reachedEnd && scrollHeight - scrollTop <= clientHeight + 5) {
      setReachedEnd(true);
    }
  };

  return (
    <main>
      <Header header={header} />
      <div className="page">
        <div className="content">
          <TermsOfUse
            onContinueClick={onContinueClick}
            termOfUseOnScroll={termOfUseOnScroll}
            checkFirstBox={() => setFirstBoxChecked(!firstBoxChecked)}
            checkSecondBox={() => setSecondBoxChecked(!secondBoxChecked)}
            continueBtnEnabled={continueBtnEnabled}
          />
        </div>
      </div>
      <Footer />
    </main>
  );
}

TOU.propTypes = {
  page: PropTypes.shape({
    header: PropTypes.shape({
      name: PropTypes.string.isRequired
    })
  }).isRequired
};
