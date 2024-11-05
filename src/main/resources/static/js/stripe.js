const stripe = Stripe('pk_test_51QF5ipG6oZPN22hpE6rOCseRSuSwwCu4ZpY2huQBBBDoXbcruPqRn8gRJiqM1bDMFtPJEeGT8QgcasOICWWELkfs00d2AfP54G');
 const paymentButton = document.querySelector('#paymentButton');
 
 paymentButton.addEventListener('click', () => {
   stripe.redirectToCheckout({
     sessionId: sessionId
   })
 });